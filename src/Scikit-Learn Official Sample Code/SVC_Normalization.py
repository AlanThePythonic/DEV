# Normalization

from sklearn import preprocessing
import numpy as np

# a = np.array([[10, 2.7, 3.6], [-100, 5, -2], [120, 20, 40]], np.float64)

# print(a)
# print(preprocessing.scale(a))

from sklearn.cross_validation import train_test_split
from sklearn.datasets.samples_generator import make_classification
from sklearn.svm import SVC
import matplotlib.pyplot as plt

X, Y = make_classification(n_samples=100, n_features=2, n_redundant=0, n_informative=2,
                           random_state=22, n_clusters_per_class=1, scale=100)

X = preprocessing.scale(X)  # Increase the Accuracy

plt.scatter(X[:, 0], X[:, 1], c=Y)  # Use Y to seperate the type of each dot
plt.show()

print(X)
print()
print(" ------------------ ")
X_train, X_test, Y_train, Y_test = train_test_split(X, Y, test_size=0.5)
print(X_train)
print()
print(X_test)
print()
print(Y_train)
print()
print(Y_test)
print()
clf = SVC()
clf.fit(X_train, Y_train)
print(clf.score(X_test, Y_test))
