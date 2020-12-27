class MinQ {
    constructor(max, comparator) {
        this.pq = [null];
        // this.maxSize = max;
        this.comparator = comparator;
    }

    swim(ind) {
        while (ind > 1) {
            let j = Math.floor(ind / 2);
            if (!this.isLess(ind, j)) break;

            this.swap(ind, j);
            ind = j;
        }
    }

    sink(ind) {
        while (ind * 2 < this.pq.length) {
            let j = ind * 2;
            if (j < this.pq.length - 1 && this.isLess(j + 1, j)) j++;
            if (this.isLess(ind, j)) break;

            this.swap(ind, j);
            ind = j;
        }
    }

    add(val) {
        this.pq.push(val);
        this.swim(this.pq.length - 1);
        // if (this.pq.length > this.maxSize + 1) this.pq.pop();
    }

    delMin() {
        if (this.pq.length === 1) return null;
        let max = this.pq[1];
        this.pq[1] = this.pq[this.pq.length - 1];
        this.pq.pop();
        this.sink(1);
        return max;
    }

    swap(a, b) {
        [this.pq[a], this.pq[b]] = [this.pq[b], this.pq[a]];
    }

    isLess(a, b) {
        return this.comparator(this.pq[a], this.pq[b]) < 0;
    }
}