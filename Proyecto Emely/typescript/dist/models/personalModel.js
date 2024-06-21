"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.Personal = exports.GeneroEnum = void 0;
const typeorm_1 = require("typeorm");
const roleModel_1 = require("./roleModel");
var GeneroEnum;
(function (GeneroEnum) {
    GeneroEnum["Masculino"] = "Masculino";
    GeneroEnum["Femenino"] = "Femenino";
    GeneroEnum["Otro"] = "Otro";
})(GeneroEnum = exports.GeneroEnum || (exports.GeneroEnum = {}));
let Personal = class Personal {
};
__decorate([
    (0, typeorm_1.PrimaryGeneratedColumn)(),
    __metadata("design:type", Number)
], Personal.prototype, "ID", void 0);
__decorate([
    (0, typeorm_1.Column)(),
    __metadata("design:type", String)
], Personal.prototype, "Nombre", void 0);
__decorate([
    (0, typeorm_1.Column)(),
    __metadata("design:type", String)
], Personal.prototype, "Apellido", void 0);
__decorate([
    (0, typeorm_1.Column)({ type: 'date', nullable: true }),
    __metadata("design:type", Date)
], Personal.prototype, "Fecha_Nacimiento", void 0);
__decorate([
    (0, typeorm_1.Column)({ type: 'enum', enum: GeneroEnum, nullable: true }),
    __metadata("design:type", String)
], Personal.prototype, "Genero", void 0);
__decorate([
    (0, typeorm_1.Column)({ nullable: true }),
    __metadata("design:type", String)
], Personal.prototype, "Telefono", void 0);
__decorate([
    (0, typeorm_1.Column)({ nullable: true }),
    __metadata("design:type", String)
], Personal.prototype, "Email", void 0);
__decorate([
    (0, typeorm_1.Column)({ nullable: true }),
    __metadata("design:type", String)
], Personal.prototype, "Direccion", void 0);
__decorate([
    (0, typeorm_1.Column)({ type: 'date', nullable: true }),
    __metadata("design:type", Date)
], Personal.prototype, "Fecha_Contratacion", void 0);
__decorate([
    (0, typeorm_1.Column)({ nullable: true }),
    __metadata("design:type", Number)
], Personal.prototype, "ID_Rol", void 0);
__decorate([
    (0, typeorm_1.ManyToOne)(() => roleModel_1.Roles, roles => roles.personal),
    __metadata("design:type", roleModel_1.Roles)
], Personal.prototype, "Roles", void 0);
Personal = __decorate([
    (0, typeorm_1.Entity)()
], Personal);
exports.Personal = Personal;
