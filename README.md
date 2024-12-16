## 🙌 안녕하세요. Pick, Fit 백엔드입니다.
## 🛠 기능 엿보기
- [마이페이지](#MyPage)
- [Virtual Try On](#Virtual)
- [위시리스트](#Wishlist)
- [OAuth2 구글로그인](#Google)

## 주요 기능

## MyPage
이 자리에 간략하게 마이페이지 로직에 대해 간단하게 설명해주세요..
```js
여기는 마이페이지 코드 영역입니다..
```











## Virtual
이 자리에 간략하게 파이썬 코드에 대한 로직을 설명해주세요..
```js
 @PostMapping("/process") // POST 요청을 처리
    public ResponseEntity<?> processTryOn(@RequestBody TrymeonDTO trymeonDTO) {
        String clothImageUrl = trymeonDTO.getClothImageUrl(); // DTO에서 옷 이미지 URL을 추출
        String personImageUrl = trymeonDTO.getPersonImageUrl(); // DTO에서 모델 이미지 URL을 추출
        String bigCategory = trymeonDTO.getBigCategory(); // DTO에서 대분류 카테고리를 추출

           File tempJsonFile; // FastAPI로 보낼 JSON 파일 선언
        try {
            tempJsonFile = File.createTempFile("data", ".json"); // 임시 JSON 파일 생성
            try (FileWriter writer = new FileWriter(tempJsonFile)) { // 파일에 데이터를 쓰기 위한 FileWriter 생성
                writer.write(String.format(
                        "{\"person_url\":\"%s\",\"cloth_url\":\"%s\",\"category_analysis\": {\"big_category\": \"%s\"}}",
                        personImageUrl, clothImageUrl, bigCategory // JSON 형식의 데이터 작성
                ));
            }
```python
# 호출 된 json 파일 url load
contents = await file.read()
    data = json.loads(contents)
    person_url = data.get("person_url")
    cloth_url = data.get("cloth_url")
    category_analysis = data.get("category_analysis", {})
    big_category = category_analysis.get("big_category")
# CatVTON Pipeline
def apply_virtual_tryon(catvton_pipeline, person_image, clothing_image, mask_image, output_path):
    generator = torch.Generator(device="cuda").manual_seed(seed)
    results = catvton_pipeline(
        person_image,
        clothing_image,
        mask_image,
        num_inference_steps=50,
        guidance_scale=2.5,
        height=1024,
        width=768,
        generator=generator,
        eta=1.0
    )
    repaint_result = repaint(person_image, mask_image, results[0])
    repaint_result.save(output_path)
    return output_path
# EndPoint
apply_virtual_tryon(app.state.catvton_pipeline, person_image, clothing_image, mask_image, output_path)
return {"message": "Done", "url": f"/static/{output_file_name}"}
```js
ResponseEntity<String> response = restTemplate.exchange(
                    catVtonApiUrl + "/upload/", // FastAPI의 업로드 엔드포인트
                    HttpMethod.POST, // POST 요청
                    requestEntity, // 요청 데이터
                    String.class // 응답 데이터를 문자열로 받음
            );

// FastAPI 응답 검증 및 저장
if (imageUrl == null || imageUrl.isEmpty()) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid response from FastAPI.");
}

TrymeonEntity savedImage = trymeonService.saveTrymeonImage( // 결과 이미지를 데이터베이스에 저장
        imageUrl, // 결과 이미지 URL
        trymeonDTO.getUserEmail(), // 사용자 이메일
        trymeonDTO.getProductId() // 상품 ID
);
```
<위시리스트 등록 기준>
필수 값 검증: userEmail(사용자 이메일)과 productId(상품 ID)는 필수 입력값입니다.
동일한 userEmail과 productId를 가진 항목이 이미 존재하는 경우, 해당 항목이 삭제(isDeleted=true) 상태라면 이를 복구합니다.
이미 활성(isDeleted=false) 상태인 항목은 중복 등록을 허용하지 않으며, 예외를 발생시킵니다.
새롭게 등록된 항목은 기본적으로 활성 상태(isDeleted=false)로 저장됩니다.

            
```





## Wishlist

```js

@Transactional
public WishlistEntity addToWishlist(WishlistDto wishlistDto) {
    if (wishlistDto == null) {
        throw new IllegalArgumentException("위시리스트 요청 데이터가 비어 있습니다.");
    }

    // 필수 값 검증
    if (wishlistDto.getUserEmail() == null || wishlistDto.getUserEmail().isEmpty()) {
        throw new IllegalArgumentException("유효하지 않은 이메일입니다.");
    }
    if (wishlistDto.getProductId() == null) {
        throw new IllegalArgumentException("상품 ID가 누락되었습니다.");
    }

    // 기존 위시리스트 항목 조회
    Optional<WishlistEntity> optionalProduct = wishlistRepository.findByProductIdAndUserEmail(
            wishlistDto.getProductId(),
            wishlistDto.getUserEmail()
    );

    if (optionalProduct.isPresent()) {
        WishlistEntity existingProduct = optionalProduct.get();

        if (existingProduct.isDeleted()) {
            // 삭제 상태인 항목 복구
            existingProduct.setDeleted(false);
            existingProduct.setImageUrl(wishlistDto.getImageUrl());
            existingProduct.setUserName(wishlistDto.getUserName());
            existingProduct.setPrice(wishlistDto.getPrice());
            existingProduct.setTitle(wishlistDto.getTitle());
            return wishlistRepository.save(existingProduct);
        } else {
            // 이미 활성화된 항목 처리
            throw new IllegalStateException("이미 활성 상태로 등록된 위시리스트 항목입니다.");
        }
    }

    // 새로운 위시리스트 항목 생성
    WishlistEntity newProduct = new WishlistEntity();
    newProduct.setUserEmail(wishlistDto.getUserEmail());
    newProduct.setImageUrl(wishlistDto.getImageUrl());
    newProduct.setUserName(wishlistDto.getUserName());
    newProduct.setPrice(wishlistDto.getPrice());
    newProduct.setProductId(wishlistDto.getProductId());
    newProduct.setTitle(wishlistDto.getTitle());
    newProduct.setDeleted(false); // 기본적으로 활성 상태로 저장

    return wishlistRepository.save(newProduct);
}




```









## Google
```js
여기는 구글 로그인 코드 영역입니다..
```

## Link   
- [🙋‍♂️ MyPage 코드 보러가기](https://github.com/Pick-Fit/Back-end/blob/main/PickFit/src/main/java/com/pickfit/pickfit/oauth2/model/controller/UserController.java)
- [🙋‍♂️ Virtual 코드 보러가기](https://github.com/Pick-Fit/Back-end/tree/main/PickFit/src/main/java/com/pickfit/pickfit/trymeon)  
- [🙋‍♂️ Wishlist 코드 보러가기](https://github.com/Pick-Fit/Back-end/tree/main/PickFit/src/main/java/com/pickfit/pickfit/wishlist)
- [🙋‍♂️ Google 로그인 코드 보러가기](https://github.com/Pick-Fit/Back-end/tree/main/PickFit/src/main/java/com/pickfit/pickfit/oauth2)

