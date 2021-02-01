FROM mysql:5.7

# Environment variables
ENV MYSQL_ROOT_PASSWORD root123
ENV MYSQL_DATABASE insertions

#COPY backup/ /docker-entrypoint-initdb.d/

RUN chown -R mysql:mysql /docker-entrypoint-initdb.d/

CMD ["mysqld", "--character-set-server=utf8mb4", "--collation-server=utf8mb4_unicode_ci"]