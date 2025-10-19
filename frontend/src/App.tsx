import React, {useEffect, useMemo, useState} from 'react'
import {Snack} from "./Snack";
import {service} from "./service";
import {Cart} from "./Cart";
import {Status} from "./status";

function euro(n: number): string {
    return new Intl.NumberFormat(undefined, {style: 'currency', currency: 'EUR'}).format(n)
}

export default function App(): React.JSX.Element {
    const [snacks, setSnacks] = useState<Snack[]>([]);
    const [balance, setBalance] = useState<number>(0);
    const [order, setOrder] = useState<Cart>({order: {}, amount: 0});
    const [status, setStatus] = useState<Status>(Status.Nothing);

    useEffect(() => {
        service.fetchSnack().then(setSnacks);
        service.balance().then(setBalance);
    }, []);

    const toPay = useMemo(() => {
        return order.amount;
    }, [order, snacks])

    const selectedLabels = useMemo(() => {
        return snacks
            .filter(s => (order.order[s.name] ?? 0) > 0)
    }, [order, snacks]);

    const pay = async () => {
        setStatus(Status.Pending);
        const balance = await service.pay();
        setOrder({order: {}, amount: 0});
        setBalance(balance);
        await pollStatus();
    }

    const resetStatus = async () => {
        setStatus(Status.Nothing);
    }
    const pollStatus = async () => {
        const status = await service.status();
        console.log(status, status === Status.Done);
        if (status === Status.Pending) {
            setTimeout(pollStatus, 1000);
            return;
        }

        if (status === Status.Done || status === Status.Failed) {
            setStatus(status);
            setTimeout(resetStatus, 1000);
            return;
        }
    }

    function addSnack(snack: Snack) {
        service.order(snack.name).then(u => setOrder(u));
    }

    function removeSnack(snack: Snack) {
        service.remove(snack.name).then(u => setOrder(u));
    }

    return (
        <div>
            {/* Hero Account */}
            <section className="hero is-info is-bold">
                <div className="hero-body">
                    <div className="container is-flex is-flex-direction-row is-justify-content-space-between">
                        <div className="">
                            <p className="heading">Available</p>
                            <p className="title is-2">{euro(balance)}</p>
                            { /* <button className="status-pay-circle button is-secondary is-large" onClick={refill}>Refill</button> */}
                        </div>
                        <div className="tags">
                            {selectedLabels.length === 0 ? (
                                <span className="tag is-light">No snacks selected</span>
                            ) : (
                                selectedLabels.map(p => (<>
                                        {order.order[p.name] === 1 && (
                                            <span key={p.name} className="tag is-warning is-light">{p.name}</span>)}
                                        {order.order[p.name] > 1 && (<span key={p.name}
                                                                           className="tag is-warning is-light">{p.name} x {order.order[p.name]}</span>)}
                                    </>
                                ))
                            )}
                        </div>
                        <div className="">
                            <p className="heading">To pay</p>
                            <p className="title is-4">{euro(toPay)}</p>
                        </div>
                    </div>
                </div>
            </section>

            {/* Cards grid */
            }
            <div className="container mt-4">
                <div className="scrollable-cards">
                    <div className="columns is-multiline">
                        {snacks.map(snack => (
                            <div key={snack.id} className="column is-12-mobile is-6-tablet is-4-desktop">
                                <div className={`card ${!snack.available ? 'is-disabled' : ''}`}>
                                    <div className="card-image">
                                        <figure className="image is-4by3">
                                            <img src={snack.imageURL} alt={snack.name}/>
                                        </figure>
                                    </div>
                                    <div className="card-content">
                                        <p className="title is-5">{snack.name}</p>
                                        <p className="subtitle is-6">{euro(snack.price)}</p>
                                        <div className="content">
                                            {snack.description}
                                        </div>
                                        <div className="buttons mt-3">
                                            <button className="button is-primary" disabled={!snack.available}
                                                    onClick={() => addSnack(snack)}>Add
                                            </button>
                                            {(order.order[snack.name] ?? 0) > 0 && (
                                                <button className="button is-danger is-light"
                                                        onClick={() => removeSnack(snack)}>Remove</button>
                                            )}
                                        </div>
                                    </div>
                                </div>
                            </div>
                        ))}
                    </div>
                </div>
            </div>

            <div className="container is-flex is-justify-content-center is-fullwidth mt-2">
                <button id="pay" type="button" aria-label="Pay"
                        disabled={status !== Status.Nothing || order.amount === 0}
                        className={`status-pay-circle button is-primary is-large ${status === Status.Pending ? 'is-loading is-pending' : status === Status.Done ? 'is-loading is-success' : status === Status.Failed ? 'is-loading is-failed' : ''}`} onClick={pay}>
                    {
                        status === Status.Nothing ? 'Pay' : status === Status.Pending ? 'Wait...' : status === Status.Done ? 'Done' : 'Failed'
                    }
                </button>
            </div>
        </div>
    )
}
