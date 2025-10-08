import React, {useEffect, useMemo, useState} from 'react'
import {Snack} from "./Snack";
import {service} from "./service";

function euro(n: number): string {
    return new Intl.NumberFormat(undefined, {style: 'currency', currency: 'EUR'}).format(n)
}

export default function App(): React.JSX.Element {
    /*const snacks: Snack[] = useMemo(() => [
        {
            id: 1,
            name: 'Chips',
            description: 'Crispy salted potato chips.',
            image: 'https://images.unsplash.com/photo-1641693148759-843d17ceac24?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
            price: 1.2,
            available: false
        },
        {
            id: 2,
            name: 'Chocolate',
            description: 'Delicious dark chocolate bar.',
            image: 'https://images.unsplash.com/photo-1614088685112-0a760b71a3c8?q=80&w=3333&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
            price: 1.8,
            available: true
        },
        {
            id: 3,
            name: 'Cookies',
            description: 'Buttery chocolate chip cookies.',
            image: 'https://images.unsplash.com/photo-1499636136210-6f4ee915583e?q=80&w=2678&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
            price: 1.5,
            available: true
        },
        {
            id: 4,
            name: 'Soda',
            description: 'Refreshing sparkling soda.',
            image: 'https://images.unsplash.com/photo-1579684971280-0783c9cc00bc?q=80&w=1335&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
            price: 1.0,
            available: true
        },
        {
            id: 5,
            name: 'Gummies',
            description: 'Fruity gummy bears.',
            image: 'https://plus.unsplash.com/premium_photo-1669547518632-9e50db122033?q=80&w=3687&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
            price: 1.1,
            available: true
        },
        {
            id: 6,
            name: 'Nuts',
            description: 'Roasted salty peanuts.',
            image: 'https://images.unsplash.com/photo-1605024344839-e6e41aea6b23?q=80&w=2274&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
            price: 1.6,
            available: true
        },
        {
            id: 7,
            name: 'Popcorn',
            description: 'Light and fluffy popcorn.',
            image: 'https://images.unsplash.com/photo-1512149177596-f817c7ef5d4c?q=80&w=1300&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
            price: 1.3,
            available: true
        },
        {
            id: 8,
            name: 'Granola',
            description: 'Healthy granola bar.',
            image: 'https://images.unsplash.com/photo-1504708706948-13d6cbba4062?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
            price: 1.4,
            available: true
        },
        {
            id: 9,
            name: 'Water',
            description: 'Pure spring water.',
            image: 'https://images.unsplash.com/photo-1595994195534-d5219f02f99f?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D',
            price: 0.9,
            available: true
        }
    ], [])*/

    const [snacks, setSnacks] = useState<Snack[]>([]);

    const [available] = useState<number>(10);
    const [cart, setCart] = useState<Record<number, number>>({});

    useEffect(() => {
        service.fetchSnack().then(setSnacks);
    }, []);

    const toPay = useMemo(() => {
        return snacks.reduce((sum, s) => sum + (cart[s.id] ?? 0) * s.price, 0)
    }, [cart, snacks])

    const selectedLabels = useMemo(() => {
        return snacks
            .filter(s => (cart[s.id] ?? 0) > 0)
    }, [cart, snacks])

    function addSnack(snack: Snack) {
        setCart(prev => ({...prev, [snack.id]: (prev[snack.id] ?? 0) + 1}))
    }

    function removeSnack(snack: Snack) {
        setCart(prev => {
            const current = prev[snack.id] ?? 0
            if (current <= 1) {
                const {[snack.id]: _omit, ...rest} = prev
                return rest
            }
            return {...prev, [snack.id]: current - 1}
        })
    }

    return (
        <div>
            {/* Hero Account */}
            <section className="hero is-info is-bold">
                <div className="hero-body">
                    <div className="container is-flex is-flex-direction-row is-justify-content-space-between">
                        <div className="">
                            <p className="heading">Available</p>
                            <p className="title is-2">{euro(available)}</p>
                            { /* <button className="status-pay-circle button is-secondary is-large" onClick={refill}>Refill</button> */ }
                        </div>
                        <div className="tags">
                            {selectedLabels.length === 0 ? (
                                <span className="tag is-light">No snacks selected</span>
                            ) : (
                                selectedLabels.map(p => (<>
                                    {cart[p.id] === 1 && (<span key={p.name} className="tag is-warning is-light">{p.name}</span>)}
                                    {cart[p.id] > 1 && (<span key={p.name} className="tag is-warning is-light">{p.name} x {cart[p.id]}</span>)}
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
                                            <button className="button is-primary" disabled={!snack.available} onClick={() => addSnack(snack)}>Add</button>
                                            {(cart[snack.id] ?? 0) > 0 && (
                                                <button className="button is-danger is-light" onClick={() => removeSnack(snack)}>Remove</button>
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
            <button type="button" aria-label="Pay" className="status-pay-circle button is-primary is-large">
                Pay
            </button>
            </div>
        </div>
    )
}
