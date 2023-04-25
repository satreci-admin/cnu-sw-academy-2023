import styled from "styled-components";
import ListItem from "./ListItem";

const ListHeader = styled.div`
  padding: 10px 20px;
  border-bottom: 1px solid rgba(0, 0, 0, 0.2);
`;
const Item = styled.span`
  width: 23%;
  margin-right: 20px;
  display: inline-block;
  text-align: center;
`;
const List = ({ type, info }) => {
  if (type === "robot") {
    return (
      <>
        <ListHeader>
          <Item>Robot Name</Item>
          <Item>IP</Item>
          <Item>Port</Item>
          <Item></Item>
        </ListHeader>
        {info.map((v, idx) => {
          return <ListItem type="robot" key={idx} {...v} />;
        })}
      </>
    );
  } else if (type === "task") {
    return (
      <>
        <ListHeader>
          <Item>Task Name</Item>
          <Item>Repetition</Item>
          <Item>Memo</Item>
          <Item></Item>
        </ListHeader>
        {info.map((v, idx) => {
          return <ListItem type="task" key={idx} {...v} />;
        })}
      </>
    );
  } else if (type === "member") {
    return (
      <>
        <ListHeader>
          <Item>User Name</Item>
          <Item>Email</Item>
          <Item></Item>
          <Item></Item>
        </ListHeader>
        {info.map((v, idx) => {
          return <ListItem type="member" key={idx} {...v} />;
        })}
      </>
    );
  } else {
    return null;
  }
};

export default List;
