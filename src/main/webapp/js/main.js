require("./../css/style.less");

import App from './components/App';
import React from 'react';
import ReactDOM from 'react-dom';
import { createStore } from 'redux';
import reducer from './reducers/index';
import { Provider } from 'react-redux';

import { loadAvailableLeagues } from './api/external-api';
import { initLocalize, initLocalizeData } from './api/user-api.js';

export const store = createStore(reducer, window.__REDUX_DEVTOOLS_EXTENSION__ &&
    window.__REDUX_DEVTOOLS_EXTENSION__());

loadAvailableLeagues();
initLocalize();
initLocalizeData();

ReactDOM.render(
    <Provider store={store}>
        <App />
    </Provider>,
    document.getElementById('root')
);
