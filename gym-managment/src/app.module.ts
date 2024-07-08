import { Module } from '@nestjs/common';
import { GraphQLModule } from '@nestjs/graphql';
import { ApolloDriver, ApolloDriverConfig } from '@nestjs/apollo';
import { join } from 'path';
import { PersonalModule } from "./modules/personal/personal.module";
import { TypeOrmModule } from "@nestjs/typeorm";
import { Personal } from "./modules/personal/personal.entity";
import { Rol } from "./modules/rol/rol.entity";
import { Cliente } from "./modules/cliente/cliente.entity";
import { ClientesModule } from "./clientes/clientes.module";

@Module({
  imports: [
    GraphQLModule.forRoot<ApolloDriverConfig>({
      driver: ApolloDriver,
      autoSchemaFile: join(process.cwd(), 'src/schema.gql'),
    }),
    TypeOrmModule.forRoot({
      type: 'mysql',
      host: 'localhost',
      port: 3306,
      username: 'root',
      password: '',
      database: 'proyecto2',
      entities: [Personal, Cliente, Rol],
      synchronize: true,
    }),
    PersonalModule,
    ClientesModule,
  ],
  controllers: [],
  providers: [],
})
export class AppModule {}
