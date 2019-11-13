import React from "react";
import List from "./components/List";
import dummyItems from "./items.json";
export default class App extends React.Component {
  state = {
    favItems:[]
  };
  
  handleItemClick = item => {
    //immutability
    const newItems = [...this.state.favItems];
    const newItem = { ...item};
    const targetInd = newItems.findIndex(it => it.id === newItem.id);
    if (targetInd < 0) newItems.push(newItems);
    else newItems.splice(targetInd, 1);
    this.setState({favItems:newItems});
  };
  // for class based component, you need to provide render
  // function
  render() {
    return (
      <div className="container-fluid">
      <h1 className="text-center">
        Welcome!
        <small>Class-based</small>
      </h1>
      <div className="container pt-3">
        <div className="row">
          <div className="col-sm">
          <List
          title="Our Menu"
          items={dummyItems}
          onItemClick={() => {}}
          />
          </div>
        </div>
      </div>
      </div>
);
}
}