// let initialArray = [2, 8, 1, 9, 34, 59, 87, 16, 0, 44, 66, 77, 88, 14];


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
// array = initialArray.slice();
// InsertionSort.sort(array);
// console.log("InsertionSort", array);
