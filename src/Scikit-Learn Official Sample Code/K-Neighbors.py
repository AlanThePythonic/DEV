from sklearn import datasets
from sklearn.cross_validation import train_test_split
from sklearn.neighbors import KNeighborsClassifier

# Flower graphics
iris = datasets.load_iris()
irisX = iris.data  # Describles the properties of flowers
irisY = iris.target  # Classification

X_train, X_test, Y_train, Y_test = train_test_split(
    irisX, irisY, test_size=0.5)  # Separate which data be used on Testing and Taring and mess them up for better analytic

knn = KNeighborsClassifier(n_neighbors=10)
knn.fit(X_train, Y_train)  # Training ...
print(knn.predict(X_test))
print(Y_test)
print(knn.score(X_test, Y_test))
