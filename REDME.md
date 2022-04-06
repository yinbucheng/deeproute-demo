测试用的请求

```
1:helloWord
curl --location --request GET --X GET 'http://127.0.0.1:8080/demo/test/helloWord' \
--header 'User-Agent: Apipost client Runtime/+https://www.apipost.cn/'

2:保存数据
curl --location --request POST --X POST 'http://127.0.0.1:8080/demo/test/save' \
--header 'User-Agent: Apipost client Runtime/+https://www.apipost.cn/' \
--header 'Content-Type: application/json' \
--data '{
    "title":"标题",
    "subTitle":"副标题",
    "referCnt":100
}'

3：更新数据
curl --location --request POST --X POST 'http://127.0.0.1:8080/demo/test/update' \
--header 'User-Agent: Apipost client Runtime/+https://www.apipost.cn/' \
--header 'Content-Type: application/json' \
--data '{
    "id":11,
    "title":"修改标题",
    "subTitle":"修改副标题"
}'

4：查询数据
curl --location --request POST --X POST 'http://127.0.0.1:8080/demo/test/list' \
--header 'User-Agent: Apipost client Runtime/+https://www.apipost.cn/' \
--header 'Content-Type: application/json' \
--data '{
    "pageNum":1,
    "pageSize":5
}'
```