import {RiotComponentExport} from 'riot'
import {StoreDetail} from '../../axios-front/model/store-detail';

export interface StoreDetailComponentState {
    store?: StoreDetail;
}

export interface StoreDetailComponentProps {
}

export interface StoreDetailComponent extends RiotComponentExport<StoreDetailComponentProps, StoreDetailComponentState> {
    state: StoreDetailComponentState;

    storeFind(): void;
}

export interface StoreListComponentState {
    stores?: Array<StoreDetail>;
}

export interface StoreListComponentProps {
}

export interface StoreListComponent extends RiotComponentExport<StoreListComponentProps, StoreListComponentState> {
    state: StoreListComponentState;

    storeSearch(): void;
}