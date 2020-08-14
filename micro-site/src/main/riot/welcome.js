import {component, register} from 'riot'
import {Route, Router} from '@riotjs/route'

import Welcome from './components/welcome.riot'
import Login from "./components/login.riot";
import Home from "./components/home.riot";

// グローバルコンポーネントとして登録
register('router', Router);
register('route', Route);
register('login', Login);
register('home', Home);

// グローバルに登録せずに直接コンポーネントを生成＆マウント
component(Welcome)(document.getElementById('welcome'));