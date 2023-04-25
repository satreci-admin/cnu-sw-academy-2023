import styled from "styled-components";
import { FiTrash2, FiMoreHorizontal } from "react-icons/fi";

const ListItemWrapper = styled.div`
  padding: 10px 20px;
  border-bottom: 1px solid rgba(0, 0, 0, 0.2);
`;
const Item = styled.span`
  width: 23%;
  margin-right: 20px;
  display: inline-block;
  text-align: center;
`;

const TrashIcon = styled(FiTrash2)`
  margin-right: 20px;
  cursor: pointer;
  font-size: 20px;
`;
const MoreIcon = styled(FiMoreHorizontal)`
  cursor: pointer;
  font-size: 20px;
`;

const ListItem = ({ type, ...props }) => {
  if (type === "robot") {
    const { name, ip, port, id } = props;
    return (
      <ListItemWrapper data-id={id}>
        <Item>{name}</Item>
        <Item>{ip}</Item>
        <Item>{port}</Item>
        <Item>
          <TrashIcon />
          <MoreIcon />
        </Item>
      </ListItemWrapper>
    );
  } else if (type === "task") {
    const { name, task, memo, id } = props;
    return (
      <ListItemWrapper data-id={id}>
        <Item>{name}</Item>
        <Item>{task}</Item>
        <Item>{memo}</Item>
        <Item>
          <TrashIcon />
          <MoreIcon />
        </Item>
      </ListItemWrapper>
    );
  } else if (type === "member") {
    const { name, email, id } = props;
    return (
      <ListItemWrapper data-id={id}>
        <Item>{name}</Item>
        <Item>{email}</Item>
        <Item></Item>
        <Item>
          <TrashIcon />
          <MoreIcon />
        </Item>
      </ListItemWrapper>
    );
  } else {
    return null;
  }
};

export default ListItem;
