# voyageBlog
spring 개인과제 나만의 블로그 만들기

프로젝트 설명& API 명세서 & 실행화면... : https://boundless-pudding-4e9.notion.site/189b7be021e5442993234dcbb0d185e3?pvs=4
--------------------

**과제 요구사항**

1. Entity를 그대로 반환하지 말고, DTO에 담아서 반환해주세요!
2. 과제에는 여러분들이 서버 로직에 더 집중하실 수 있도록 JSON을 반환하는 API형태로 진행하려고 합니다.
3. Postman 활용

----------------------

**API 요구사항**

1. 아래의 요구사항을 기반으로 Use Case 그려보기
    - 손으로 그려도 됩니다.

2. 전체 게시글 목록 조회 API
    - 제목, 작성자명, 작성 내용, 작성 날짜를 조회하기
    - 작성 날짜 기준 내림차순으로 정렬하기
      
3. 게시글 작성 API 
    - 제목, 작성자명, 비밀번호, 작성 내용을 저장하고
    - 저장된 게시글을 Client 로 반환하기
      
4. 선택한 게시글 조회 API 
    - 선택한 게시글의 제목, 작성자명, 작성 날짜, 작성 내용을 조회하기 

5. 선택한 게시글 수정 API
    - 수정을 요청할 때 수정할 데이터와 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후
    - 제목, 작성자명, 작성 내용을 수정하고 수정된 게시글을 Client 로 반환하기
      
6. 선택한 게시글 삭제 API
    - 삭제를 요청할 때 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후
    - 선택한 게시글을 삭제하고 Client 로 성공했다는 표시 반환하기


---------------
