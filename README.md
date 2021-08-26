# jpashop
Spring Boot와 JPA 활용 Study

---

<b> [엔티티 설계시 주의점] </b>
- 엔티티에는 가급적 Setter를 사용하지 말자
  - Setter가 모두 열려있는 경우, 변경 포인트가 많아 유지보수가 어려움. 
  
- 모든 연관관계는 지연로딩으로 설정! 
  - 즉시로딩(EAGER)은 예측이 어렵고, 어떤 SQL이 실행될지 추적하기 어려움. 
  - 특히 JPQL을 실행할 때 N+1 문제가 자주 발생 
  - 실무 -> 모든 연관관계 지연로딩(LAZY)로 설정
  - 연관된 엔티티를 함께 DB에서 조해해야 하면, "fetch join" 또는 "엔티티 그래프 기능"을 사용한다.
  - @XToOne(OneToOne, ManyToOne)관계는 기본이 즉시 로딩이므로 직접 지연 로딩으로 설정 
  
- 컬렉션은 필드에서 초기화 하자.
  - null 문제에서 안전.
  - Hibernate는 Entity를 영속화 할 때, 컬렉션을 감싸서 Hibernate가 제공하는 내장 컬렉션으로 변경

<b> [애플리케이션 아키텍처] </b>

Controller -> Service -> Repository -> DB

- 계층형 구조 사용 
  - controller, web: 웹 계층
  - service: 비즈니스 로직, 트랜잭션 처리
  - repository: JPA 직접 사용, 엔티티 매니저 활용 
  - domain: 엔티티가 모여 있는 계층 (모든 계층에서 사용) ex: User, Order, Category 등등 

- 개발 순서: Service, Repository 게층을 개발하고, 테스트 케이스를 작성해서 검증한 후, 마지막 웹 계층 적용 

<b> [Spring Boot 기술 설명] </b>
- @Repository: 스프링 빈으로 등록, JPA예외를 스프링 기반 예외로 변환 
- @PersistenceContext: 엔티티 매니저(EntityManager) 주입 
- @PersistenceUnit: 엔티티 매니저 팩토리 주입 
- @Transactional: 트랜잭션, 영속성 컨텍스트
  - readonly=true: 데이터의 변경이 없는 읽기 전용 메서드에 사용, 영속성 컨텍스트를 플러시 하지 않으므로 약간의 성능 향상 
- @NoArgsConstructor: 파라미터가 없는 생성자를 생성한다. 
  - (access = AccessLevel.PROTECTED): 해당 설정을 통해 접근 제한을 설정하여 생성자를 통한 초기화를 막을 수 있다. 

- 생성자 주입 방식을 권장하는 이유
  - 변경 불가능한 안전한 객체 생성 가능
  - final 키워드를 추가하면 컴파일 시점에 memberRepository를 설정하지 않는 오류를 체크할 수 있다. (보통 기본 생성자를 추가할 때 발견)

- 도메인 모델 패턴: 엔티티가 비즈니스 로직을 가지고 객체 지향의 특성을 적극 활용하는 것 
- 트랜잭션 스크립트 패턴: 엔티티에는 비즈니스 로직이 거의 없고 서비스 계층에서 대부분의 비즈니스 로직을 처리하는 것 

<b> [JUnit 기술 설명] </b>

- @RunWith(SpringRunner.class): 스프링과 테스트 통합 잔행 
- @SpringBootTest: 스프링 부트 띄우고 테스트(이게 없으면 @Autowired 실패 발생)
- @Transactional: 반복 가능한 테스트 지원, 각각의 테스트를 실행할 때마다 트랜잭션을 시작하고 테스트가 끝나면 트랜잭션을 강제로 롤백

---------

참조: 실전! 스프링 부트와 JPA 활용1 (인프런 김영한님의 강의)
