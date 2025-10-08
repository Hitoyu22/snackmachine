import {Snack} from "./Snack";

export const service = {
    async fetchSnack(): Promise<Snack[]> {
        const res = await fetch('/snacks');
        if (!res.ok) throw new Error(`HTTP error! Status: ${res.status}`);
        return await res.json();
    },
};

export const api = {
    async rover(): Promise<void> {
        //const res = await fetch('/rover');
        //if (!res.ok) throw new Error(`HTTP error! Status: ${res.status}`);
        //return await res.json();
        throw new Error('not implemented');
    }
};