# jpashop
Spring Boot와 JPA 활용 Study

---

[엔티티 설계시 주의점]
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
  
