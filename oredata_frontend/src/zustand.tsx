import { create } from "zustand";

export interface IBear {
    bears: number;
    increasePopulation: () => void;
}

export const useStore = create((set) => ({
    bears: 0,
    increasePopulation: () => set((state: IBear) => ({ bears: state.bears + 1 })),
}))