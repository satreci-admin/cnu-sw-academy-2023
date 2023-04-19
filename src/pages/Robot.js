import React from "react";
import MainContentWrapper from "../components/MainContentWrapper";
import Header from "../components/Header";

function Robot() {
  return (
    <MainContentWrapper>
      <Header
        mainHeader={"로봇 관리"}
        subHeader={"Supporting robot management"}
      ></Header>
      robot
    </MainContentWrapper>
  );
}

export default Robot;
