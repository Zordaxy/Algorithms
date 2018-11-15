let initialArray = [2, 8, 1, 9, 34, 59, 87, 16, 0];

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
        for(let i = 1; i < array.length; i++) {
            j = i;
            elToInsert = array[i];
            while (j > 0 && elToInsert < array[j-1]) {
                array[j] = array[j-1];
                j--;
            }
            array[j] = elToInsert;
        }
    }
}
array = initialArray.slice();
InsertionSort.sort(array);
console.log("InsertionSort", array);

class ShellSort {
    static sort(array) {





        return array;
    }
}
array = initialArray.slice();
result = ShellSort.sort(array);
console.log("ShellSort    ", array, "Not implemented");













class MergeSort {
    static sort(array) {





        return array;
    }
}
array = initialArray.slice();
result = MergeSort.sort(array);
console.log("MergeSort    ", array, "Not implemented");

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
