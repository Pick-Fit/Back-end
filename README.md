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
```js
버츄얼 파이썬
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
```

```js
백엔드 스프링부트
```








## Wishlist
```js
여기는 위시리스트 코드 영역입니다..

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

