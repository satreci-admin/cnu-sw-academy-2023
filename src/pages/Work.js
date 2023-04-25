import React from "react";
import MainContentWrapper from "../components/MainContentWrapper";
import Header from "../components/Header";
import List from "../components/List";
import { DUMMYTask } from "../DUMMY";

function Work() {
  return (
    <MainContentWrapper>
      <Header
        mainHeader={"작업 명세서 관리"}
        subHeader={"Task specification management"}
      ></Header>
      <List type="task" info={DUMMYTask.tasks} />
    </MainContentWrapper>
  );
}

export default Work;
