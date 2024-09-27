# 팀 삼삼오오
<br/>
<p align="center">
  <img alt="로고" src="https://image.istarbucks.co.kr/common/img/main/rewards-logo.png" width="250" height="250">
</p>


### 🛒 프로젝트 개요

> 프로젝트 팀명 : 스파로스 5기 삼삼오오
프로젝트 기간 : 2024.08.05 ~ 2024.10.01
> 
> 
> 프로젝트 소개 : 스타벅스 온라인 스토어에 대하여 기획, 설계, 개발, 배포 전 과정에 직접 하나하나 구현하는 것
>

### 💻 기획 배경


### 📝 프로젝트 목표

- 프로젝트 리빌딩을 통해 백엔드의 부족한 기술 스택을 보완하고, API 설계, 데이터베이스 연동, 서버 관리 등 다양한 백엔드 기술을 체계적으로 익히는 것을 목표입니다.
  또한 이 과정을 통해 실제 서비스와 유사한 기능을 구현하고, 실제 개발 환경에서 사용하는 협업 툴(GitHub, CI/CD 등)을 활용하여 실무에서의 프로젝트 진행 방식을 체득하는 것입니다.

## 2. 프로젝트 멤버

|   **Name**   |김현진|오대관|서정원|정훈석|권성현|
| :----------: | :-----: | :-----: | :-----: | :-----: | :-----:|
| **Position** |팀장 <br/> 백엔드|인프라 <br/> 백엔드|백엔드|프론트엔드|프론트엔드|
|   **Git**    |개인계정 작성필요| |daegwan00|


## 3. 개발 환경 및 사용 기술

### 🔨 개발 언어 및 도구

- **BE** `Spring boot(3.2.9)` **,** `IntelliJ(제일 최신 버전 작성)`

<img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white"> <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=SprigBoot&logoColor=white"> <img src="https://img.shields.io/badge/SpringSecurity-6DB33F?style=for-the-badge&logo=SpringSecurity&logoColor=white"> <img src="https://img.shields.io/badge/MySql-4479A1?style=for-the-badge&logo=MySql&logoColor=white"> <img src="https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=Redis&logoColor=white"> <img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=Gradle&logoColor=white"> <img src="https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=white">


- **공통**

<img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white"> <img src="https://img.shields.io/badge/Vercel-000000?style=for-the-badge&logo=Vercel&logoColor=white"> <img src="https://img.shields.io/badge/AWS-DD344C?style=for-the-badge&logo=amazonaws&logoColor=white"> <img src="https://img.shields.io/badge/postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white"> ![GitHub Actions](https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=github-actions&logoColor=white)

<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"> <img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white">

### **🛠 사용한 라이브러리 및 프레임워크**

| 분류 | 기술 스택 |
| --- | --- |
| Common | GitHub, Notion, Figma, Git-Flow |
| Back-end | JDK(17), gradle(버전 추가), ORM (JPA), Spring Security, swagger(3) |
| CI/CD | AWS EC2, Nginx, Ubuntu(Ubuntu 20.04 LTS), Docker(27.2.0), Docker-compose(v2.5.0) |
| Database | MySQL(24.04.2), Redis(7.2.5) |

### 🔧 **서버 구성 및 데이터베이스**

- 아키텍처 구조도

- erd


### **📋** Directory 구조도

- **BE**

```bash
📔src
    ├──📔main
    │   │               
    │   ├──📔java
    │   │   └──📔com
    │   │       └──📔starbucks3355
    │   │           └──📔starbucksServer
    │   │               ├──📔auth
    │   │               │   ├──📔controller
    │   │               │   ├──📔dto
    │   │               │   ├──📔entity
    │   │               │   ├──📔repository
    │   │               │   ├──📔service
    │   │               │   └──📔vo
    │   │               |
    │   │               ├──📔category
    │   │               │   ├──📔controller
    │   │               │   ├──📔dto
    │   │               │   ├──📔entity
    │   │               │   ├──📔repository
    │   │               │   ├──📔service
    │   │               │   └──📔vo
    │   │               │
    │   │               ├──📔common
    │   │               │   ├──📔config
    │   │               │   ├──📔entity
    │   │               │   ├──📔exception
    │   │               │   ├──📔jwt
    │   │               │   ├──📔provider
    │   │               │   ├──📔s3
    │   │               │   └──📔utils
    │   │               │
    │   │               ├──📔domainCart
    │   │               │   ├──📔controller
    │   │               │   ├──📔dto
    │   │               │   ├──📔entity
    │   │               │   ├──📔repository
    │   │               │   ├──📔service
    │   │               │   └──📔vo
    │   │               │
    │   │               ├──📔domainCoupon
    │   │               │   ├──📔controller
    │   │               │   ├──📔dto
    │   │               │   ├──📔entity
    │   │               │   ├──📔repository
    │   │               │   ├──📔service
    │   │               │   └──📔vo
    │   │               │
    │   │               ├──📔domainImage
    │   │               │   ├──📔controller
    │   │               │   ├──📔dto
    │   │               │   ├──📔entity
    │   │               │   ├──📔repository
    │   │               │   ├──📔service
    │   │               │   └──📔vo
    │   │               │
    │   │               ├──📔domainMember
    │   │               │   ├──📔controller
    │   │               │   ├──📔dto
    │   │               │   ├──📔entity
    │   │               │   ├──📔repository
    │   │               │   ├──📔service
    │   │               │   └──📔vo
    │   │               │
    │   │               ├──📔domainOrder
    │   │               │   ├──📔controller
    │   │               │   ├──📔dto
    │   │               │   ├──📔entity
    │   │               │   ├──📔repository
    │   │               │   ├──📔service
    │   │               │   └──📔vo
    │   │               │
    │   │               ├──📔domainProduct
    │   │               │   ├──📔controller
    │   │               │   ├──📔dto
    │   │               │   ├──📔entity
    │   │               │   ├──📔repository
    │   │               │   ├──📔service
    │   │               │   └──📔vo
    │   │               │
    │   │               ├──📔domainPromotion
    │   │               │   ├──📔controller
    │   │               │   ├──📔dto
    │   │               │   ├──📔entity
    │   │               │   ├──📔repository
    │   │               │   ├──📔service
    │   │               │   └──📔vo
    │   │               │
    │   │               ├──📔domainReview
    │   │               │   ├──📔controller
    │   │               │   ├──📔dto
    │   │               │   ├──📔entity
    │   │               │   ├──📔repository
    │   │               │   ├──📔service
    │   │               │   └──📔vo
    │   │               │
    │   │               ├──📔shipping
    │   │               │   ├──📔controller
    │   │               │   ├──📔dto
    │   │               │   ├──📔entity
    │   │               │   ├──📔repository
    │   │               │   ├──📔service
    │   │               │   └──📔vo
    │   │               │
    │   │               ├──📔vendor
    │   │                   ├──📔controller
    │   │                   ├──📔dto
    │   │                   ├──📔entity
    │   │                   ├──📔repository
    │   │                   ├──📔service
    │   │                   └──📔vo
    │   │                   
    │   └──📔resources
    └──📔test
```

		

## 4. 기능 소개 

### ⚙️ 기능 소개(백엔드)
- 상품 (기획전, 필터, 검색)
- 리뷰
- 이미지
- 장바구니
- 배송지
- 결제 (카카오페이)
- 주문
- 카테고리



## 개발 후기

| 💻 <br>  |
|:-------|

|  <br>  |
|:-------|

|  <br> |
|:-------|

|  <br>  |
오대관: 스타벅스 프로젝트 리빌딩을 통해 백엔드 개발을 하며 많은 경험을 하였습니다. 특히 제가 부족하다고 느꼈던 API 설계, 데이터베이스 구조화 등 관련된 기술을 경험 할 수 있는 좋은 기회였습니다
처음 경험해본 인프라, ci/cd 전반적인 인프라의 흐름을 알 수 있었습니다. 또한 협업과 관련해서 처음이였는데 깃 허브 사용과 코드 리뷰등 팀원들과 소통하는 방법을 배웠습니다. 처음으로 하는 대규모 프로젝트
였어서 개인적인 시간까지 포기하면서 공부를 하고 개발을 하였는데 다시 돌이켜보면 너무 부담감을 가지고 했던 것 같습니다. 다음 프로젝트를 할때는 부담감을 좀 내려놓고 임해야겠습니다

|  <br> |
|:-------|



