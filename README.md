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
		productstock:[
				psid: String,
				psize: String,
				pcid: String,
			     ],
			     []...
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
 pname: String
 pcode: String
 depth1name:String
 depth2name:String
 depth3name:String
 startdate:Date
 enddate:Date
 pstatus: int
 pageNo: int
}
```

`GET` **/admin/product/{pid}** : 상품상세보기


`POST` **/admin/product/modifiy** : 상품수정
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

## 주문관리


### 주문목록

`POST` **/admin/order/list** : 주문목록 가져오기(필터검색)
```
{
 oid: String
 pcode: String
 ophone: String
 ostatus: String
 mname: String
 mid: String
 startdate: Date
 enddate: Date
 psid: String
 pageNo: int
}
```

`GET` **/admin/order/list/{oid}** : 주문상세 가져오기(상품검색)

`POST` **/admin/order/updatestatus** : 주문상태 변경하기
```
{
oids:[ String, String, ...],
status: String
}
```
