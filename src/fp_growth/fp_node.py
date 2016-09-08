class fp_tree_node:

    # Initial node element
    def __init__(self,
                 parent_node=None,
                 left_node=None,
                 right_node=None,
                 product_code="",
                 num_of_product=0):
        self.parent_node = parent_node
        self.left_node = left_node
        self.right_node = right_node
        self.product_code = product_code
        self.num_of_product = num_of_product

    # Set the left node of current node
    def set_left_node(self, node):
        self.left_node = node
        self.left_node.parent_node = self

    # Set the right node of current node
    def set_right_node(self, node):
        self.right_node = node
        self.right_node.parent_node = self

    # Get product code of current node
    def set_product_code(self, product_code):
        self.product_code = product_code
        self.num_of_product += 1

    # Get the left node of current node
    def get_left_node(self):
        return self.left_node

    # Get the right node of current node
    def get_right_node(self):
        return self.right_node

    # Get the product_code of current node
    def get_the_product_code(self) -> str:
        return self.product_code

    # Get total no of product of current node
    def get_the_no_of_node(self) -> int:
        return self.num_of_product
