import React, { useState } from "react";
import MainContentWrapper from "../components/MainContentWrapper";
import Header from "../components/Header";
import List from "../components/List";
import { DUMMYTask } from "../DUMMY";
import AddButton from "../components/AddButton";
import TaskInputModal from "../components/TaskInputModal";

function Work() {
  const [open, setOpen] = useState(false);
  const handleClick = () => {
    setOpen(!open);
  };

  return (
    <MainContentWrapper>
      <Header
        mainHeader={"작업 명세서 관리"}
        subHeader={"Task specification management"}
      ></Header>
      <AddButton onClick={handleClick} />
      <TaskInputModal open={open} onClick={handleClick} />
      <List type="task" info={DUMMYTask.tasks} />
    </MainContentWrapper>
  );
}

export default Work;
