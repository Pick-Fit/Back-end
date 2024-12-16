## 🙌 안녕하세요. Pick, Fit 백엔드입니다.
## 🛠 기능 엿보기
- [마이페이지](#MyPage)
- [Virtual Try On](#Virtual)
- [위시리스트](#Wishlist)
- [OAuth2 구글로그인](#Google)

## 주요 기능

## MyPage
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
```js
```python
contents = await file.read()
    data = json.loads(contents)
    person_url = data.get("person_url")
    cloth_url = data.get("cloth_url")
    category_analysis = data.get("category_analysis", {})
    big_category = category_analysis.get("big_category")

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
이 자리에 호출하는 방식, 요청하는 방식에 대한 설명을 간략하게 적어주세요.

            
```





## Wishlist
이 자리에 위시리스트 관련 로직에 대한 설명을 간략하게 추가해주세요..
```js
여기는 위시리스트 코드 영역입니다..
@Table(name = "wishlist")  // "wishlist" 테이블과 매핑
public class WishlistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // ID 자동 생성
    private Integer id;

    @Column(name = "user_email", nullable = false)  // 사용자 이메일 컬럼
    private String userEmail;

    @Column(name = "image_url", nullable = false)  // 상품 이미지 URL 컬럼
    private String imageUrl;

    @Column(name = "user_name", nullable = false)  // 사용자 이름 컬럼
    private String userName;

    @Column(name = "price", nullable = false)  // 상품 가격 컬럼
    private String price;

    @Column(name = "product_id", nullable = false)  // 상품 ID 컬럼
    private Long productId;

    @Column(name = "title", nullable = false)  // 상품 제목 컬럼
    private String title;

    @Column(name = "isDeleted")
    private boolean isDeleted = false; // 기본값: false (삭제되지 않은 상태)


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

