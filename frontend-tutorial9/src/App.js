import React, { Component } from "react";
import Layout from "./components/Layout/Layout";
import Restorans from "./containers/Restorans/Restorans";
class App extends Component {
  render() {
    return (
      <Layout>
        <Restorans />
      </Layout>
    );
  }
}
export default App;
