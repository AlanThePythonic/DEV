import pymysql


class mysql_connection:

    # Configuration of Connection
    def __init__(self):
        self.connection = pymysql.connect(host='localhost', user='root', password='87iu0080A',
                                          db='lab', charset='utf8mb4', cursorclass=pymysql.cursors.DictCursor)

    # Return connection object
    def get_connection(self):
        return self.connection

    # Close Connection
    def close_connection(self):
        self.connection.close()
