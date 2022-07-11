import React from 'react';
import {BrowserRouter} from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.min.js';

import RouterComponent from './router/RouterComponent';

function App() {
    return (
        <BrowserRouter>
            <RouterComponent/>

        </BrowserRouter>
    );
}

export default App;