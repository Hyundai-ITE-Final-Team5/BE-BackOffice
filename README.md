## 상품관리


### 상품등록

`POST` **/admin/product/registration** : 상품등록
```
{
productCommon:{
		pid: String,
		pname: String,
		pnote: String,
		bno: int,
		pstatus: int,
		preleasedate: Date,
		productcolor:[
				pcid: String,
				pcimg1: String,
				pcimg2: String,
				pcimg3: String,
				pcchipimg: String,
				pccolorcode: String,
				pcprice: int,
				pid: String,
			     ],
			     []...
	      }
}
```

`GET` **/admin/product/duplicatesearch/{pname}** : 중복상품 검색
```
{ pname: String }
```

-----------------------------------------
### 상품목록

`POST` **/admin/product/productlist** : 상풍목록 가져오기 (필터검색)
```
{
 pageNo: int,
 filter: {
	 pname: String
	 pcode: String
	 depth1name:String
	 depth2name:String
	 depth3name:String
	 startdate:Date
	 enddate:Date
	 displaystatus:boolean
	 salestatus:boolean
         }
}
```

`GET` **/admin/product/{pid}** : 상품상세보기(수정기능 추가예정)


-----------------------------------------
### 상품분류관리

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

