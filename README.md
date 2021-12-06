## 상품관리

### 상품등록

`GET` **/admin/product/classification** : 카테고리 대분류 목록, 브랜드 목록(처음 화면 나올때 필요한 데이터)

`GET` **/admin/product/addBrand** : 브랜드 추가
```
{ brandName: String }
```

`GET` **/admin/product/removeBrand** : 브랜드 삭제
```
{ bno: int }
```

`GET` **/admin/product/getcategorydepth2** : depth1의 중분류 가져오기
```
{ depth1: String }
```

`GET` **/admin/product/getcategorydepth3** : depth2의 소분류 가져오기
```
 {
  depth1: String,
  depth2: String,
 }
```

-----------------------------------------

