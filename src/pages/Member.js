import React from "react";
import MainContentWrapper from "../components/MainContentWrapper";
import Header from "../components/Header";
import List from "../components/List";
import { DUMMYMember } from "../DUMMY";

function Member() {
  return (
    <MainContentWrapper>
      <Header mainHeader={"사용자 관리"} subHeader={"User Management"}></Header>
      <List type="member" info={DUMMYMember.member} />
    </MainContentWrapper>
  );
}

export default Member;
