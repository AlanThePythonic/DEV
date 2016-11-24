from fp_mysql_connention import mysql_connection
from itertools import groupby


def get_result_from_db():

    # Capture the transaction list from database
    connection = mysql_connection()
    result = ""

    try:
        with connection.get_connection().cursor() as cursor:

            # Read a single record
            sql = 'select * from transaction'
            cursor.execute(sql)
            result = cursor.fetchall()

    finally:
        connection.close_connection()
        return result


def group_by_client():

    # Group the capture result list of database
    result_list = sorted(list(map(lambda x: (x.get("CLIENT_ID"), x.get(
        "STOCK_ID"), x.get("BUY_QUANTITY")), get_result_from_db())
    ), key=lambda x: x[0])

    group_result_list = [(key, [i[1] for i in sorted(values)])
                         for key, values in groupby(result_list, lambda i: i[0])]

    return group_result_list


# -- Main Portal
if __name__ == '__main__':

    print(group_by_client())
