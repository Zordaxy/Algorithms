let initialArray = [2, 8, 1, 9, 34, 59, 87, 16, 0, 44, 66, 77, 88, 14];

class BubbleSort {
    static sort(array) {
        for (let i = array.length; i > 0; i--) {
            for (let j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) this._swap(array, j, j + 1);
            }
        }
    }

    static _swap(array, a, b) {
        let temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
let array = initialArray.slice();
BubbleSort.sort(array);
console.log("BubbleSort   ", array);

class SelectionSort {
    static sort(array) {
        let minIndex;
        for (let i = 0; i < array.length; i++) {
            minIndex = i;
            for (let j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) minIndex = j;
            }
            this._swap(array, i, minIndex);
        }
    }

    static _swap(array, a, b) {
        let temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
array = initialArray.slice();
SelectionSort.sort(array);
console.log("SelectionSort", array);

class InsertionSort {
    static sort(array) {
        let j, elToInsert;
        for (let i = 1; i < array.length; i++) {
            j = i;
            elToInsert = array[i];
            while (j > 0 && elToInsert < array[j - 1]) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = elToInsert;
        }
    }
}
array = initialArray.slice();
InsertionSort.sort(array);
console.log("InsertionSort", array);

class MergeSort {
    static sort(array) {
        if (array.length < 2) return array;
        let middle = Math.floor(array.length / 2);

        return this._merge(this.sort(array.slice(0, middle)), this.sort(array.slice(middle)));
    }

    static _merge(a, b) {
        let result = [];
        let leftPointer = 0, rightPointer = 0;
        let aLength = a.length;
        let bLength = b.length;

        while (leftPointer < aLength && rightPointer < bLength) {
            if (a[leftPointer] < b[rightPointer]) {
                result.push(a[leftPointer++]);
            } else {
                result.push(b[rightPointer++]);
            }
        }
        return result.concat(a.slice(leftPointer)).concat(b.slice(rightPointer));
    }
}
array = initialArray.slice();
result = MergeSort.sort(array);
console.log("MergeSort    ", result);

class MergeSortSameArray {
    static sort(array) {
        this.array = array;
        this.aux = array.slice();
        this._mergeSort(0, array.length - 1);
        return this.array;
    }

    static _mergeSort(start, end) {
        if (end <= start) return;
        let middle = Math.floor((end + start) / 2);
        this._mergeSort(start, middle);
        this._mergeSort(middle + 1, end);
        this._merge(start, middle, end);
    }

    static _merge(start, middle, end) {
        let leftPointer = start, rightPointer = middle + 1;
        this.aux = array.slice(start, end + 1);

        for (let k = start; k <= end; k++) {
            if (leftPointer > middle) this.array[k] = this.aux[rightPointer++ - start];
            else if (rightPointer > end) this.array[k] = this.aux[leftPointer++ - start];
            else if (this.aux[leftPointer - start] < this.aux[rightPointer - start]) this.array[k] = this.aux[leftPointer++ - start];
            else this.array[k] = this.aux[rightPointer++ - start];
        }
    }
}
array = initialArray.slice();
result = MergeSortSameArray.sort(array);
console.log("MS same array", result);





class QuickSort {
    static sort(array) {


        return array;
    }
}
array = initialArray.slice();
result = QuickSort.sort(array);
console.log("QuickSort    ", array, "Not implemented");

class HeapSort {
    static sort(array) {


        return array;
    }
}
array = initialArray.slice();
result = HeapSort.sort(array);
console.log("HeapSort     ", array, "Not implemented");
