
# Over-fitting : Over-looked to the accuracy, It will make a lot of noise
# and could not be a curve


from sklearn import datasets
from sklearn.cross_validation import train_test_split
from sklearn.neighbors import KNeighborsClassifier
from sklearn.cross_validation import cross_val_score
import matplotlib.pyplot as plt

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

# ---------- Cross Validation ------------ #
knn = KNeighborsClassifier(n_neighbors=5)
scores = cross_val_score(knn, irisX, irisY, cv=5, scoring="accuracy")
print(scores)
print(scores.mean())

# ---------- Find the best fit of K-Neighbors to prevent overfitting --------- #
k_range = range(1, 31)
k_scores = []
k_loss = []

for k in k_range:
    knn = KNeighborsClassifier(n_neighbors=k)
    loss = -cross_val_score(knn, irisX, irisY, cv=10,
                            scoring='mean_squared_error')  # Regression is better
    scores = cross_val_score(knn, irisX, irisY, cv=10,
                             scoring="accuracy")  # Suits for Classification, cv : Seperated 10 sets
    k_scores.append(scores.mean())
    k_loss.append(loss.mean())


plt.figure(1)
plt.subplot(211)
plt.plot(k_range, k_scores, 'b')
plt.xlabel("Value of K for KNN")
plt.ylabel("Cross-Validated Accuracy")
plt.subplot(212)
plt.plot(k_range, k_loss, 'b-')
plt.xlabel("Loss of K for KNN")
plt.ylabel("Cross-Validated Accuracy")
plt.show()
