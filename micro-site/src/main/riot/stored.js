import {component, register} from 'riot'
import {Route, Router} from '@riotjs/route'
import Stored from './components/stored.riot'

import ContactList from "./components/contact/contact-list.riot";

// グローバルコンポーネントとして登録
register('router', Router);
register('route', Route);

register('contact-list', ContactList);

// グローバルに登録せずに直接コンポーネントを生成＆マウント
component(Stored)(document.getElementById('stored'));