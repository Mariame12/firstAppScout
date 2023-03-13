import {RemoteApp} from '@eclipse-scout/core';
import * as contactscout from './index';

Object.assign({}, contactscout); // Use import so that it is not marked as unused

new RemoteApp().init();
