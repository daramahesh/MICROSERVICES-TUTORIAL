import React from 'react';
import "./Home.css";
import Header from '../../components/header/Header';
import Featured from '../../components/featured/Featured';
import PropertyList from '../../components/propertyList/PropertyList';
import FeaturedProperties from '../../components/featuredProperties/FeaturedProperties';
import MailList from '../../components/mailList/MailList';

const Home = () => {
  return (
    <>
      <Header/>
      <div className="homeContainer">
        <Featured />
        <h1 className="homeTitles">Browse By Property Type</h1>
        <PropertyList />
        <h1 className="homeTitles">Homes guests love</h1>
        <FeaturedProperties />
        <MailList />
      </div>
    </>
  );
}

export default Home