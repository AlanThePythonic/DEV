import numpy as np


array = np.array([[1, 2, 3], [4, 5, 6]], dtype=np.int)

print(array)

print(" ------------------------ ")

print('number of dimension : {0}, {1}'.format(array.ndim, array.dtype))

print(" ------------------------ ")

print('Shape : {0}, Size : {1}'.format(array.shape, array.size))

print(" ------------------------ ")

print('number of dimension : {0}'.format(array.ndim))

print(" ------------------------ ")

array2 = np.zeros((3, 3))
print("np.zeros : {}".format(array2))

print(" ------------------------ ")

array3 = np.arange(10).reshape((2, 5))

print("np.arange(10).reshape((2, 5) : {}".format(array3))

print(" ------------------------ ")

array4 = np.linspace(1, 10, 6).reshape(2, 3)

print("np.linspace(1, 10, 6).reshape(2, 3) : {}".format(array4))

print(" ------------------------ ")

array5 = np.array([10, 20, 30, 40])
array6 = np.arange(4)
print("array5 ** array6 : {}".format(array5 ** array6))
print(10 * np.sin(array5))
print("array5 < 30, array5 == 3 : {}:".format(array5 < 30, array5 == 3))

print(" ------------------------ ")

array7 = np.random.random_integers(low=1, high=12, size=10)
print("Total : {0}".format(list(array7)))
print("Sum : {0}".format(np.sum(array7)))
print("Min : {0}".format(np.min(array7)))
print("Max : {0}".format(np.max(array7)))

print(" ------------------------ ")

array8 = np.random.random((3, 4))
print("Random Number with format (3,4) : {}".format(array8))

# Axis = 1 -> Row
# Axis = 0 -> Column
print("Find Min per row : {}".format(np.min(array8, axis=1)))
print("Find Max per row : {}".format(np.max(array8, axis=1)))
print("Sum: {}".format(np.sum(array8, axis=1)))

print(" ------------------------ ")

array9 = np.arange(0, 20).reshape(5, 4)
print(array9)
print("Find Min : {}".format(np.argmin(array9)))
print("Find Max : {}".format(np.argmax(array9)))
print("Average of array9 : {}".format(np.mean(array)))
print("Average of array9 : {}".format(np.average(array)))
print("Median : {}".format(np.median(array9)))
print("Accumulate : {}".format(np.cumsum(array9)))
array9 = np.linspace(start=1, stop=10, num=5, dtype=np.int32)
print(list(array9))
print("Show diff between elements : {}".format(np.diff(array9)))
print("Sorted : {}".format(np.sort(array9)[::-1]))

print(" ------------------------ ")

array9 = np.arange(0, 12).reshape(3, 4)
print("Reset : {}".format(array9))
print("Transpose : {}".format(np.transpose(array9)))

print(" ------------------------ ")

array10 = np.arange(0, 12).reshape(3, 4)
print("np.clip : {0}".format(np.clip(array10, 5, 9)))
array10 = np.arange(0, 12).reshape(3, 4)
print("array10 : {0}".format(array10))
print("np.mean : {0}".format(np.mean(array10, axis=0)))

print(" ------------------------ ")

array11 = np.arange(3, 15).reshape((3, 4))
print("array11[1][3] : {0}".format(array11[1][3]))
print("array11.flatten : {0}".format(array11.flatten()))
for column in array11.transpose():
    print(column)

print(" ------------------------ ")

array12 = np.arange(1, 10)
array13 = np.arange(11, 20)
print("np.vstack : {0}".format(
    np.vstack((array12, array13))))  # Vertical Stack
# Horizontal Stack
print("np.hstack : {0}".format(np.hstack((array12, array13))))
print("Changed Dimension : {}".format(array12[np.newaxis, :]))
print("Changed Dimension :  {}".format(array12[:, np.newaxis]))
print("Flatten : {}".format(array12[:, np.newaxis].flatten()))
print("Concatenate : {}".format(np.concatenate((array12, array13), axis=0)))
print("concatenate 2 : {}".format(np.concatenate(
    (array12[:, np.newaxis], array13[:, np.newaxis]), axis=1)))

print(" ------------------------ ")

array14 = np.arange(12).reshape((3, 4))
print(array14)
print("vsplit function : {}".format(np.vsplit(array14, 3)))
print("hsplit function : {}".format(np.hsplit(array14, 4)))
print("array_split : {}".format(np.array_split(array14, 4)))
print(array14 is array13)

print(" ------------------------ ")

array15 = array13.copy()  # Deep Copy
print("Deep Copy : {}".format(array15))
