import React from "react";
import classes from "./Restoran.module.css";

const Restoran = props => {
    return (
        <div className={classes.Restoran}>
            <h3>
                {props.nama}
            </h3>
            <p>
                Alamat : {props.alamat}
            </p>
            <p>
                Nomor Telepom : {props.nomorTelepon}
            </p>
        </div>
    )
}

export default Restoran;