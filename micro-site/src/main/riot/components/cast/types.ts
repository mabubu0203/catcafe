import {RiotComponentExport} from 'riot'
import {CastDetail} from '../../axios-front/model/cast-detail';

export interface CastDetailComponentState {
    cast?: CastDetail;
}

export interface CastDetailComponentProps {
}

export interface CastDetailComponent extends RiotComponentExport<CastDetailComponentProps, CastDetailComponentState> {
    state: CastDetailComponentState;
}

export interface CastListComponentState {
    casts?: Array<CastDetail>;
}

export interface CastListComponentProps {
}

export interface CastListComponent extends RiotComponentExport<CastListComponentProps, CastListComponentState> {
    state: CastListComponentState;

    castSearch(e): void;
}