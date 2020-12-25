class PriorityQ {
    //  max q
    constructor() {
        this.pq = [null];
    }

    add(value) {
        this.pq.push(value);
        this.swim(this.pq.length - 1);
    }

    removeMax() {
        if (!this.length() === 1) return null;
        let max = this.pq[1];
        this.pq[1] = this.pq[this.pq.length - 1];
        this.sink(1);
        this.pq.length = this.pq.length - 1;
        return max;
    }

    swim(index) {
        while (index > 1) {
            let j = Math.floor(index / 2);
            if (this.pq[index] < this.pq[j]) break;

            this.exchange(index, j);
            index = j;
        }
    }

    sink(index) {
        while (index * 2 <= this.pq.length) {
            j = index * 2;
            if (j < this.pq.length && this.pq[j] < this.pq[j + 1]) j++;
            if (this.pq[index] >= this.pq[j]) break;

            this.exchange(index, j);
            index = j;
        }
    }

    exchange(a, b) {
        [this.pq[a], this.pq[b]] = [this.pq[b], this.pq[a]];
        // let temp = this.pq[a];
        // this.pq[a] = this.pq[b];
        // this.pq[b] = temp;
    }

    length() {
        return this.pq.length - 1;
    }
}


let pq = new PriorityQ();
let testArray = [2, 8, 1, 9, 34, 59, 87, 16, 0, 44, 66, 77, 88, 14];
for (let number of testArray) pq.add(number);

let length = pq.length();
console.log("length:", length);
let result = [];
for (let i = 0; i < length; i++) result.push(pq.removeMax());
console.log("length after", length, "removes:", pq.length());
console.log("should be ordered descending:", result);
