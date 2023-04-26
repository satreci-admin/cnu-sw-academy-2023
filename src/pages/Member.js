import React, { useState } from "react";
import MainContentWrapper from "../components/MainContentWrapper";
import Header from "../components/Header";
import List from "../components/List";
import { DUMMYMember } from "../DUMMY";
import AddButton from "../components/AddButton";
import MemberInputModal from "../components/MemberInputModal";

function Member() {
  const [open, setOpen] = useState(false);
  const handleClick = () => {
    setOpen(!open);
  };

  return (
    <MainContentWrapper>
      <Header mainHeader={"사용자 관리"} subHeader={"User Management"}></Header>
      <AddButton onClick={handleClick} />
      <MemberInputModal open={open} onClick={handleClick} />
      <List type="member" info={DUMMYMember.member} />
    </MainContentWrapper>
  );
}

export default Member;
