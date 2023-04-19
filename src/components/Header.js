import styled from "styled-components";

const HeaderWrapper = styled.div`
  background-color: #fffbfe;
  padding: 5px 15px;
  border-radius: 10px;
`;

const MainHeader = styled.h3`
  margin-bottom: -15px;
`;

const SubHeader = styled.h4`
  font-weight: 400;
`;

const Header = ({ mainHeader, subHeader }) => {
  return (
    <HeaderWrapper>
      <MainHeader>{mainHeader}</MainHeader>
      <SubHeader>{subHeader}</SubHeader>
    </HeaderWrapper>
  );
};

export default Header;
