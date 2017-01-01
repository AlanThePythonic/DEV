import pandas as pd
import pymysql.cursors


def df_builder(sql, cursor):
    cursor.execute(sql)
    result = cursor.fetchall()
    return pd.DataFrame.from_dict(result)


# Connect to the database
connection = pymysql.connect(host='localhost',
                             user='root',
                             password='87iu0080A',
                             db='lab',
                             charset='utf8mb4',
                             cursorclass=pymysql.cursors.DictCursor)

try:
    with connection.cursor() as cursor:

        df_user = df_builder("select * from user", cursor)
        df_blog = df_builder("select * from blog", cursor)
        df_post = df_builder("select * from post", cursor)

        print(" ------------------------ ")
        postByBlog = df_post[df_post.blog_id == 1]
        print(" ------------------------ ")
        blogByUser = df_user[df_user.user_id == 1]
        print(" ------------------------ ")
        result = pd.merge(postByBlog, blogByUser, how='left',
                          on='blog_id', indicator=True)
        print(result[['user_id', 'blog_id', 'post_id', 'title', 'content']])


finally:
    connection.close()
