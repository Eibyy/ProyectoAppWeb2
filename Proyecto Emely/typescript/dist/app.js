"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
require("reflect-metadata");
const express_1 = __importDefault(require("express"));
const typeorm_1 = require("typeorm");
const apollo_server_express_1 = require("apollo-server-express");
const type_graphql_1 = require("type-graphql");
const personalSchema_1 = require("./schema/personalSchema");
const roleSchema_1 = require("./schema/roleSchema");
const personalRoutes_1 = __importDefault(require("./routes/personalRoutes"));
const roleRoutes_1 = __importDefault(require("./routes/roleRoutes"));
const authRoutes_1 = __importDefault(require("./routes/authRoutes"));
const ormconfig_1 = __importDefault(require("./ormconfig/ormconfig"));
const dotenv_1 = __importDefault(require("dotenv"));
const authMiddleware_1 = __importDefault(require("./middleware/authMiddleware"));
dotenv_1.default.config();
(async () => {
    await (0, typeorm_1.createConnection)(ormconfig_1.default);
    const app = (0, express_1.default)();
    app.use(express_1.default.json());
    // Rutas de autenticación
    app.use('/auth', authRoutes_1.default);
    // Rutas protegidas
    app.use('/personal', authMiddleware_1.default, personalRoutes_1.default);
    app.use('/roles', authMiddleware_1.default, roleRoutes_1.default);
    const schema = await (0, type_graphql_1.buildSchema)({
        resolvers: [personalSchema_1.PersonalResolver, roleSchema_1.RoleResolver],
    });
    const server = new apollo_server_express_1.ApolloServer({ schema });
    await server.start();
    server.applyMiddleware({ app });
    const PORT = process.env.PORT || 3000;
    app.listen(PORT, () => {
        console.log(`Server is running on port ${PORT}`);
    });
})();
