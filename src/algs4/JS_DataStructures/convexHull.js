class ConvexHull {

    /**
     * Graham scan implementation to calculate Convex Hull
     */
    convexHull(array) {
        if (array.some(el => !(el instanceof Point))) {
            throw(new TypeError('Array contains elements other than Point'));
        }

        array.sort((a, b) => b.y - a.y);
        array.sort(array[0].polarOrder());

        const ch = []; // result Convex Hull
        ch.push(array[0]);
        ch.push(array[1]);

        let top;
        for (let i = 2; i < array.length; i++) {
            top = ch.pop();
            while (ch.length > 0 && ch[ch.length - 1].ccwOrder(top, array[i]) <= 0) {
                top = ch.pop();
            }
            ch.push(top);
            ch.push(array[i]);
        }

        // remove duplicates
        ch.forEach((x, index) => {
            const nextInd = (index + ch.length) % ch.length;
            if (x.equals(ch[nextInd])) {
                ch.splice(nextInd, 1);
            }
        });

        return ch;
    }
}

class Point {
    x;
    y;

    equals(pt) {
        if (!pt || !(pt instanceof Point)) {
            return null;
        }
        return this.x === pt.x && this.y === pt.y;
    }

    polarOrder(a, b) {
        if (!(a instanceof Point) || !(b instanceof Point)) {
            return null;
        }

        function order (a, b) {
            const distX1 = a.x - this.x;
            const distY1 = a.y - this.y;
            const distX2 = b.x - this.x;
            const distY2 = b.y - this.y;

            // Check possible point positions
            if (distY1 >= 0 && distY2 < 0) {
                // point 1 is above; point 2 below
                return -1;
            } else if (distY2 >= 0 && distY1 < 0) {
                // point 1 is below; point 2 above
                return 1;
            } else if (distY1 === 0 && distY2 === 0) {
                // 3-collinear and horizontal
                if (distX1 >= 0 && distX2 < 0) {
                    return -1;
                } else {
                    return distX2 >= 0 && distX1 < 0 ? 1 : 0;
                }
            } else {
                // both points are above or below
                return -this.ccwOrder(a, b);
            }
        };

        return order.bind(this);
    }

    ccwOrder(start, last) {
        if (!(start instanceof Point) || !(last instanceof Point)) {
            return null;
        }
        const triangleArea = start.x * (this.y - last.y) + this.x * (last.y - start.y) + last.x * (start.y - this.y);

        return triangleArea > 0 ? 1 : -1;
    }
}