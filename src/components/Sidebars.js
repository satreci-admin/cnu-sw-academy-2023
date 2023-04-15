import React, { useState, memo, useCallback } from "react";
import { Link, useLocation } from "react-router-dom";
import { Sidebar, Menu, MenuItem } from "react-pro-sidebar";
import styled from "styled-components";

const SideBarWrapper = styled.div`
  position: fixed;
  top: 0;
  background-color: #f9f9f9;
  height: 100%;
  width: 230px;
  padding: 10px 20px;
  z-index: 99;
`;

const SideBarHeader = styled.h2`
  margin-bottom: 20px;
`;

const StyledLink = styled(MenuItem)`
  text-decoration: none;
  font-weight: ${({ active }) => (active === "true" ? "800" : "600")};
  color: ${({ active }) =>
    active === "true" ? "black" : "rgba(0, 0, 0, 0.5)"};
  background-color: ${({ active }) =>
    active === "true" ? "#f2f2f2" : "#f9f9f9"};
  border-radius: 5px;
`;

const MemoStyledLink = memo(StyledLink);

function Sidebars() {
  const menus = [
    { name: "로봇 관리", path: "/robot" },
    { name: "작업명세서 관리", path: "/work-specification" },
    { name: "사용자 관리", path: "/member" },
  ];

  const { pathname } = useLocation();
  const [click, setClick] = useState(pathname);

  const handleClick = useCallback((path) => {
    setClick(path);
  }, []);

  return (
    <SideBarWrapper>
      <Sidebar>
        <SideBarHeader>Simple RPA</SideBarHeader>
        <Menu>
          {menus.map((menu, idx) => (
            <MemoStyledLink
              component={<Link to={menu.path} />}
              key={idx}
              active={menu.path === click ? "true" : "false"}
              onClick={() => handleClick(menu.path)}
            >
              {menu.name}
            </MemoStyledLink>
          ))}
        </Menu>
      </Sidebar>
    </SideBarWrapper>
  );
}

export default Sidebars;
