import {Snack} from "./Snack";
import {Cart} from "./Cart";
import {Status} from "./status";


export const service = {
    async fetchSnack(): Promise<Snack[]> {
        const res = await fetch('/snacks');
        if (!res.ok) throw new Error(`HTTP error! Status: ${res.status}`);
        return await res.json();
    },

    async balance(): Promise<number> {
        const res = await fetch('/balance');
        if (!res.ok) throw new Error(`HTTP error! Status: ${res.status}`);
        return await res.json();
    },

    async order(name: string): Promise<Cart> {
        const res = await fetch('/order', {
            method: 'POST',
            headers: {'Content-Type': 'application/json; charset=UTF-8'},
            body: JSON.stringify({snack: name})
        });
        if (!res.ok) throw new Error(`HTTP error! Status: ${res.status}`);
        return await res.json();
    },

    async remove(name: string): Promise<Cart> {
        const res = await fetch('/order', {
            method: 'DELETE',
            headers: {'Content-Type': 'application/json; charset=UTF-8'},
            body: JSON.stringify({snack: name})
        });
        if (!res.ok) throw new Error(`HTTP error! Status: ${res.status}`);
        return await res.json();
    },

    async pay(): Promise<number> {
        const res = await fetch('/pay', {
            method: 'POST',
            headers: {'Content-Type': 'application/json; charset=UTF-8'},
        });
        if (!res.ok) throw new Error(`HTTP error! Status: ${res.status}`);
        return await res.json();
    },

    async status(): Promise<Status> {
        const res = await fetch('/status');
        if (!res.ok) throw new Error(`HTTP error! Status: ${res.status}`);
        const value = await res.json() as string;
        return Status[value as keyof typeof Status];
    }
};