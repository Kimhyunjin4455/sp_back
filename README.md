# 삼삼오오 🥇

<p align="center">
  <img alt="로고" src="https://i.namu.wiki/i/9p8OVxJTce_f2HnuZF1QOU6qMSHqXBHdkcx3q_hlGxvhcyaOXKxBVyoDkeg-Cb4Nx2p60W0AUh6RzjAH59vHwQ.svg" width="250" height="250">
</p>



### 🛒 프로젝트 개요

> 프로젝트 팀명 : 스파로스 5기 삼삼오오
프로젝트 기간 : 2024.08.06 ~ 2024.10.01
> 
> 
> 프로젝트 소개 : 스타벅스 온라인 스토어에 대하여 기획, 설계, 개발, 배포 전 과정에 직접 하나하나 구현하는 것
>
>
<br/>

### 💻 기획 배경

 스타벅스 클론코딩 프로젝트는 현대적인 온라인 쇼핑 경험을 재현하고자 시작되었습니다. 실제 시장에서의 요구사항을 분석하여 사용자 중심의 플랫폼 구축을 목표로 하였습니다. 이 과정에서 스타벅스의 운영 방식과 고객 경험을 깊이 이해하고, 최신 기술을 적용해 보는 것이 주요 동기였습니다. 사용자 친화적인 인터페이스와 효율적인 시스템을 통해 소비자들이 더욱 쉽게 원하는 제품을 접할 수 있도록 하는 것이 이 프로젝트의 핵심입니다.
<br/>
<br/>
### 🥅 프로젝트 목표

 본 프로젝트의 목표는 사용자에게 최적화된 쇼핑 경험을 제공하며, 효율적인 데이터 관리를 통해 상품 정보의 정확성과 신뢰성을 보장하는 것입니다. 또한, 직관적인 UI/UX 디자인을 통해 사용자 편의성을 극대화하고, 모든 개발 과정에서의 협업과 최신 기술 스택을 유지하여 실제 업계 표준에 부합하는 프로젝트 구현을 목표로 합니다.
<br/>
<br/>

##  프로젝트 멤버

| **Name**     | 김현진(본인)          | 오대관  | 서정원  | 권성현   | 정훈석                                           |
|:------------:|:------------:|:----:|:----:|:-----:|:---------------------------------------------:|
| **Position** | 팀장 <br/> 백엔드 | 백엔드  | 백엔드  | 프론트엔드 | 프론트엔드                                         |
| **Git**      | [Kimhyunjin4455](https://github.com/Kimhyunjin4455)         | [daegwan00](https://github.com/daegwan00) | [joungoni](https://github.com/joungoni) | []()  | [AndreaStudy](https://github.com/AndreaStudy) |

<br/>

## 담당 역할
- 상품, 리뷰, 기획전, 회원 장바구니, 미디어 등 도메인에 관련된 API 개발
- AwsS3를 이용한 미디어 처리
- NoSql(redis)을 이용한 최근 본 상품 처리, QueryDsl의 동적 쿼리를 통한 검색 / 카테고리 분류
- 집계 테이블을 활용한 베스트 리뷰 처리

<br/>

## 성과
- 무한 스크롤 과정에서의 더 빠른 조회 방식 탐색을 위해 커서(cursor) 기반 페이징 처리 와 동적 쿼리(QueryDSL) 처리를 통해 조회 성능을 개선하여 <br/> 사용자 경험 향상, Pageable 이용했을 때
보다 **조회속도 약 20배 개선(데이터 5,000,000개 기준)**
- N+1 문제를 해결하기 위해 **패치 조인(fetch join)** 을 적용하여 불필요한 쿼리 실행을 제거하고 데이터베이스 처리 비용을 절감
- 영구적으로 저장할 필요 없는 데이터를 **Redis TTL**을 활용해 관리하여, 만료된 데이터는 자동으로 삭제되고 메모리 기반 저장소를 통해 빠른 조회 성능을 제공함으로써 시스템 성능을 개선

<br/>
 

## ⚙️ 기능 소개

1. 회원가입/로그인 : next-auth를 이용한 로그인·로그아웃, 회원가입, 아이디 찾기, 비밀번호 재설정 기능
2. 마이페이지 : 나의 주문내역, 받은 선물, 쿠폰, 배송지, 리뷰 관리 기능
3. 배송지 관리 페이지: 배송관련 생성, 수정, 삭제, 기존 배송지 변경, 배송지 정보 수집 동의 토글 기능 
4. 장바구니 : 회원/비회원 장바구니 기능. 비회원의 경우 쿠키를 발급하여 쿠키 value로 장바구니 관리.
   비회원으로 장바구니 추가후 로그인시 담아뒀던 상품 회원 장바구니로 옮겨짐
5. 찜하기 : 단일상품, 특가, 브랜드, 카테고리 중, 소 찜하기 기능
6. 카테고리 : 카테고리별로 상품이 보여지고, 정렬기능과 필터링 기능 사용 가능
7. 검색 : 키워드 위주의 검색 기능
8. 구매 : 바로구매 및 장바구니 구매를 통해 상품 구매
9. 배송 : 배송 상태 확인
10. 리뷰 : 구매한 상품 리뷰 기능

<br/>


## 개발 환경 및 사용 기술

<br/>

### 🔨 개발 언어 및 도구

- **BE** `Spring boot(3.2.9)` **,** `IntelliJ(제일 최신 버전 작성)`

<img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white"> <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=SprigBoot&logoColor=white"> <img src="https://img.shields.io/badge/SpringSecurity-6DB33F?style=for-the-badge&logo=SpringSecurity&logoColor=white"> <img src="https://img.shields.io/badge/MySql-4479A1?style=for-the-badge&logo=MySql&logoColor=white"> <img src="https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=Redis&logoColor=white"> <img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=Gradle&logoColor=white"> <img src="https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=white">

<br/>

- **FE** `TypeScript(^5)`, `Visual Studio Code(1.93.1)`

  <img src="https://img.shields.io/badge/nextdotjs-000000?style=for-the-badge&logo=nextdotjs&logoColor=white"> <img src="https://img.shields.io/badge/react-61DAFB?style=for-the-badge&logo=react&logoColor=white"> <img src="https://img.shields.io/badge/recoil-3578E5?style=for-the-badge&logo=recoil&logoColor=white">

<br/>

- **공통**

<img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white"> <img src="https://img.shields.io/badge/Vercel-000000?style=for-the-badge&logo=Vercel&logoColor=white"> <img src="https://img.shields.io/badge/AWS-DD344C?style=for-the-badge&logo=amazonaws&logoColor=white"> <img src="https://img.shields.io/badge/postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white"> ![GitHub Actions](https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=github-actions&logoColor=white)
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"> <img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white">

<br/>

### **🛠 사용한 라이브러리 및 프레임워크**

| 분류 | 기술 스택 |
| --- | --- |
| Common | GitHub, Notion, Figma, Git-Flow |
| Back-end | JDK(17), gradle, ORM (JPA), Spring Security, swagger(2.2.0) |
| Front-end | Next.js(v14.2.6), TypeScript(^5), Node.js(22.6.0), npm(10.8.3)                                     |
| CI/CD | AWS EC2, Nginx, Ubuntu(Ubuntu 20.04 LTS), Docker(27.2.0), Docker-compose(v2.5.0) |
| Database | MySQL(24.04.2), Redis(7.2.5) |

<br/>

### 🔧 **서버 구성 및 데이터베이스**



| 아키텍처 구조도 |
| --- |
|  |
| <img width="1528" alt="3355Architecture" src="https://github.com/user-attachments/assets/6b41d9bb-3dea-4c2f-97a9-7583677c0148" /> |

| erd |
| --- |
|  |
| <img width="779" alt="erd" src="https://github.com/user-attachments/assets/7dc65408-b17f-4866-adf8-e8f55fc1fd21" /> |

<br/>

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


- **FE**

```bash
📂public
|---📂assets      # 정적 이미지 파일
📂src
|---📂actions     # API fetch 함수
|---📂app         # 라우팅 관련 파일
|---📂components  # 공통 컴포넌트
|---📂config      # 라우터
|---📂context     # Session Context
|---📂datas       # 정적인 데이터
|---📂lib         # shadcn 관련 util 파일
|---📂providers   # Session, Scroll 주입하는 파일
\---📂types       # typeSciprt 관련 파일
```

		
<br/>


## 개발 후기

| 💻 김현진 <br> 저에게는 첫 팀프로젝트였습니다. 팀장을 맡으며 협업 과정에서 가장 중요한 것이 소통이라는것을 깨닫고 협업에 대한 많은 것을 배워나갈 수 있었습니다. 그간 공부하고 토이프로젝트로 진행했었던 백엔드 관련 지식을 이번 프로젝트에 쏟아낼 수 있었고 매일 과정에 몰입하는 이 경험이 저에게는 너무나도 만족스러웠습니다. QueryDsl을 학습하고 적용하였으며 조금 더 가독성이 좋은 코드를 만들기 위해 고민하였으며, api 처리 기술, 리팩토링의 중요성과 개발 과정에 못지 않게 기획 과정 또한 중요하다는 것을 이번 과정을 통해 깨달을 수 있었습니다. 이번 프로젝트는 앞으로의 좋은 발판이 될 것이라고 자신합니다. |
|:---------------------------|


<br/>



