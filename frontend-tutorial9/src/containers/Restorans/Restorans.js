import React, { Component } from "react";
import Restoran from "../../components/Restoran/Restoran";
import classes from "./Restorans.module.css";
import axios from "../../axios-restoran";
import Modal from "../../components/UI/Modal/Modal";
import Button from "../../components/UI/Button/Button";
import search from "../../components/Restoran/Restoran.module.css";

class Restorans extends Component {
    constructor(props){
        super(props);
        this.state = {
            restorans: [],
            isCreate: false,
            isLoading: true,
            nama:"",
            alamat:"",
            nomorTelepon:"",
            rating:"",
            search:""
        }
    }
    
    updateSearch(event){
        this.setState({search: event.target.value.substr(0,20)});
    }
    
    componentDidMount(){
        this.loadRestorans();
    }
    
    addRestoranHandler = () => {
        this.setState({isCreate:true});
    }
    
    canceledHandler = () => {
        this.setState({isCreate:false, isEdit: false});
    }
    
    // handler untuk perubahan setiap value pada form
    changeHandler = event => {
        // name dari prop name di input
        const {name,value} = event.target;
        this.setState({[name]:value});
    }
    
    submitAddRestoranHandler = event => {
        event.preventDefault();
        this.setState({isLoading:true});
        this.addRestoran();
        this.canceledHandler();
        this.setState({nama:"", alamat:"", nomorTelepon:"", rating:""});
    }
    
    async addRestoran() {
        const restoranToAdd = {
            nama: this.state.nama,
            alamat: this.state.alamat,
            nomorTelepon: this.state.nomorTelepon,
            rating: this.state.rating
        };

        await axios.post("/restoran", restoranToAdd);
        await this.loadRestorans();
    }
    
    submitEditRestoranHandler = event => {
        console.log("editing")
        event.preventDefault();
        this.setState({ isLoading: true });
        this.editRestoran();
        this.canceledHandler();
        this.setState({nama:"", alamat:"", nomorTelepon:"", rating:""});
    }

    async editRestoran(){
        const restoranToEdit = {
            idRestoran: this.state.idRestoran,
            nama: this.state.nama,
            alamat: this.state.alamat,
            nomorTelepon: this.state.nomorTelepon,
            rating: this.state.rating
        };

        await axios.put("/restoran/" + this.state.idRestoran, restoranToEdit);
        await this.loadRestorans();
        this.canceledHandler();
    }
    
    loadRestorans = async () => {
        const fetchedRestorans = [];
        const response = await axios.get("/restorans");
        for (let key in response.data){
            fetchedRestorans.push({
                ...response.data[key]
            });
        }
        this.setState({
            restorans: fetchedRestorans
        });
    };
    
    async deleteRestoranHandler(restoranId){
        await axios.delete(`restoran/${restoranId}`);
        await this.loadRestorans();
    }
    
    renderForm(){
        const { isEdit } = this.state;
        return(
            <form>
                <input
                className={classes.Input}
                name="nama"
                type="text"
                placeholder="Nama"
                value={this.state.nama}
                onChange={this.changeHandler}
                />
                <input
                className={classes.Input}
                name="nomorTelepon"
                type="number"
                placeholder="Nomor Telepon"
                value={this.state.nomorTelepon}
                onChange={this.changeHandler}
                />
                <textarea
                className={classes.TextArea}
                name="alamat"
                type="text"
                placeholder="Alamat"
                value={this.state.alamat}
                onChange={this.changeHandler}
                />
                <input
                className={classes.Input}
                name="rating"
                type="number"
                placeholder="Rating"
                value={this.state.rating}
                onChange={this.changeHandler}
                />
                <Button btnType="Danger" onClick={this.canceledHandler}>
                    CANCEL
                </Button>
                <Button btnType="Success" onClick={isEdit ? this.submitEditRestoranHandler : this.submitAddRestoranHandler}>
                    SUBMIT
                </Button>
            </form>
        );
    }
    // loadingHandler = () => {
    //     const currentIsLoading = this.state.isLoading;
    //     this.setState( {isLoading: !(currentIsLoading)} );
    //     console.log(this.state.isLoading);
    // }
    render() {
        // console.log("render()");
        let filteredRestorans = this.state.restorans.filter(
            (restoran) => {
                return restoran.nama.toLowerCase().indexOf(this.state.
                    search.toLowerCase()) !== -1;
            });
        return (
            <React.Fragment>
                <Modal show={this.state.isCreate || this.state.isEdit}
                    modalClosed={this.canceledHandler}>
                    {this.renderForm()}
                </Modal>
                <div className={classes.Title}> All Restorans </div>
                <div className={classes.ButtonLayout}>
                    <button
                        className={classes.addRestoranButton}
                        onClick={this.addRestoranHandler}
                    >
                        + Add New Restoran
                    </button>
                </div>
                <div className={search.Restoran}>
                    <input type = "text"
                        value={this.state.search}
                        onChange={this.updateSearch.bind(this)}/>
                </div>
                
                <div className={classes.Restorans}>
                    {this.state.restorans &&
                        filteredRestorans.map(restoran =>
                            <Restoran
                            key={restoran.id}
                            nama={restoran.nama}
                            alamat={restoran.alamat}
                            nomorTelepon={restoran.nomorTelepon}
                            edit={() => this.editRestoranHandler(restoran)}
                            delete={() => this.deleteRestoranHandler(restoran.idRestoran)}
                            />
                        )}
                </div>
                
            </React.Fragment>
        );
    }
}
export default Restorans;
