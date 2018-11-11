class PriorityQ {
    constructor() {
        this.pq = [null];
    }

    add(value) {
        this.pq.push(value);
        this.swim(this.pq.length - 1);
    }

    removeMax() {
        if (!this.length()) {
            return null;
        }
        let max = this.pq[1];
        this.pq[1] = this.pq[this.pq.length - 1];
        this.sink(1);
        this.pq.length = this.pq.length -1;
        return max;
    }

    swim(index) {
        while(index > 1) {
            if (this.pq[index] < this.pq[index/2]) {
                break;
            }
            this.exchange(index, index/2);
            index = index/2;
        }
    }

    sink(index) {
        while(index <= this.pq.length) {
            let j = index*2;
            if(j < this.pq.length && this.pq[j] < this.pq[j]) j++;
            if(this.pq[j] > this.pq[index]) {
                this.exchange(index, j);
            }
            index = j;
        }
    }

    exchange(a, b) {
        let temp = this.pq[a];
        this.pq[a] = this.pq[b];
        this.pq[b] = temp;
    }

    length() {
        return this.pq.length - 1;
    }
}

let pq = new PriorityQ();
pq.add(12);
pq.add(48);
pq.add(33);
pq.add(44);
console.log(pq.removeMax());
pq.add(17);
console.log(pq.removeMax());
console.log(pq.length());
// expect 48, 44, 3
